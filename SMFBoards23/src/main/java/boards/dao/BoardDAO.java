/*
 * BoardDAO : Oracle JDBC
 */
package boards.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardDAO {
	// private static final String SAVEFOLDER = "D:/Temp/boards/fileuploads";
	private static final String SAVEFOLDER = "D:\\Temp\\boards\\fileuploads";
	private static final String ENCTYPE = "UTF-8";
	private static int MAXSIZE = 5 * 1024 * 1024; // 5MB
	private static final int DOWNLOAD_BUFF_SIZE = 1024 * 8; // 8KB 

	private DBConnectionMgr pool;

	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시판 리스트
	public Vector<BoardBean> getBoardList(String keyField, String keyWord, int start, int end) {
		System.out.printf("[getBoardList] keyField(%s), keyWord(%s), start(%d), end(%d)\n", keyField, keyWord, start, end);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<BoardBean> vlist = new Vector<BoardBean>();
		
		try {
			con = pool.getConnection();
			if (keyWord.equals("null") || keyWord.equals("")) {
				sql = "SELECT rownum, a.* FROM (SELECT rownum rnum, b.* FROM ";
				sql += "(SELECT * FROM boards ORDER BY ref desc, pos) b) a WHERE rnum BETWEEN ? AND ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			} 
			else {
				sql = "SELECT rownum, a.* FROM (SELECT rownum rnum, b.* FROM (SELECT * FROM boards ";
				sql += "WHERE " + keyField + " like ? ";
				sql += "ORDER BY ref desc, pos) b) a WHERE a.rnum BETWEEN ? AND ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt("num"));
				bean.setName(rs.getString("name"));
				bean.setSubject(rs.getString("subject"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegdate(rs.getString("regdate"));
				bean.setCount(rs.getInt("count"));
				vlist.add(bean);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return vlist;
	}
	
	// 총 게시물수
	public int getTotalCount(String keyField, String keyWord) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			if (keyWord.equals("null") || keyWord.equals("")) {
				sql = "select count(num) from boards";
				pstmt = con.prepareStatement(sql);
			} else {
				sql = "select count(num) from boards where " + keyField + " like ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return totalCount;
	}
	
	// 게시물 입력
	public void insertBoard(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MultipartRequest multi = null;
		int filesize = 0;
		String filename = null;
		try {
			con = pool.getConnection();
			sql = "select max(num) from boards";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int ref = 1;
			if (rs.next()) {
				ref = rs.getInt(1) + 1;
			}
			
			// file upload
			File file = new File(SAVEFOLDER);
			if (!file.exists()) {
				file.mkdirs();
			}
			multi = new MultipartRequest(req, SAVEFOLDER, MAXSIZE, ENCTYPE,	new DefaultFileRenamePolicy());

			if (multi.getFilesystemName("filename") != null) {
				filename = multi.getFilesystemName("filename");
				filesize = (int) multi.getFile("filename").length();
			}
			
			String content = multi.getParameter("content");
			if (multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
				content = UtilMgr.replace(content, "<", "&lt;");
			}
			
			sql = "insert into boards(num,name,content,subject,ref,pos,depth,regdate,pass,count,ip,filename,filesize)";
			sql += "values(boards_seq.nextval,?, ?, ?, ?, 0, 0, sysdate, ?, 0, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("name"));
			pstmt.setString(2, content);
			pstmt.setString(3, multi.getParameter("subject"));
			pstmt.setInt(4, ref);
			pstmt.setString(5, multi.getParameter("pass"));
			pstmt.setString(6, multi.getParameter("ip"));
			pstmt.setString(7, filename);
			pstmt.setInt(8, filesize);
			pstmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	// 게시물 리턴
	public BoardBean getBoard(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		BoardBean bean = new BoardBean();
		try {
			con = pool.getConnection();
			sql = "select * from boards where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setNum(rs.getInt("num"));
				bean.setName(rs.getString("name"));
				bean.setSubject(rs.getString("subject"));
				bean.setContent(rs.getString("content"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegdate(rs.getString("regdate"));
				bean.setPass(rs.getString("pass"));
				bean.setCount(rs.getInt("count"));
				bean.setFilename(rs.getString("filename"));
				bean.setFilesize(rs.getInt("filesize"));
				bean.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}

	// 조회수 증가
	public void upCount(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "update boards set count=count+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// 게시물 삭제
	public void deleteBoard(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "select filename from boards where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getString(1) != null) {
				if (!rs.getString(1).equals("")) {
					File file = new File(SAVEFOLDER + "/" + rs.getString(1));
					if (file.exists())
						UtilMgr.delete(SAVEFOLDER + "/" + rs.getString(1));
				}
			}
			sql = "delete from boards where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 게시물 수정
	public void updateBoard(BoardBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "update boards set name = ?, subject=?, content = ? where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getContent());
			pstmt.setInt(4, bean.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// 게시물 답변
	public void replyBoard(BoardBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert into boards (num,name,content,subject,ref,pos,depth,regdate,pass,count,ip)";
			sql += "values(boards_seq.nextval,?,?,?,?,?,?,sysdate,?,0,?)";
			int depth = bean.getDepth() + 1;
			int pos = bean.getPos() + 1;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getContent());
			pstmt.setString(3, bean.getSubject());
			pstmt.setInt(4, bean.getRef());
			pstmt.setInt(5, pos);
			pstmt.setInt(6, depth);
			pstmt.setString(7, bean.getPass());
			pstmt.setString(8, bean.getIp());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// 답변에 위치값 증가
	public void replyUpBoard(int ref, int pos) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "update boards set pos = pos + 1 where ref = ? and pos > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, pos);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	// 파일 다운로드
	public void downLoad(HttpServletRequest req, HttpServletResponse res, JspWriter out, PageContext pageContext) {
		try {
			req.setCharacterEncoding("UTF-8");

			String filename = req.getParameter("filename");
			
			// String fullpath = UtilMgr.con(SAVEFOLDER + File.separator + filename);
			String fullpath = SAVEFOLDER + File.separator + filename;
			System.out.println("[download] fullpath="+ fullpath);
			
			//File file = new File(UtilMgr.con(SAVEFOLDER + File.separator + filename));
			File file = new File(fullpath);
			
			System.out.println("[download] filesize= "+ file.length());
			
			res.setHeader("Accept-Ranges", "bytes");
			String strClient = req.getHeader("User-Agent");
		
			System.out.println("[download] User-Agent= "+ strClient);
			
			// 윈도우 운영체제에서 파일이름을 한글로 저장 
			String downfilename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			
			if (strClient.indexOf("MSIE6.0") != -1) {
				res.setContentType("application/smnet;charset=UTF-8");
				res.setHeader("Content-Disposition", "filename=" + downfilename + ";");
			} 
			else {
				res.setContentType("application/smnet;charset=UTF-8");
				res.setHeader("Content-Disposition", "attachment;filename="+ downfilename + ";");
			}
			
			out.clear();
			out = pageContext.pushBody();
			
			if (file.isFile()) {
				/*
				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream outs = new BufferedOutputStream(res.getOutputStream());
				//byte b[] = new byte[(int) file.length()];
				int read = 0;
				while ((read = fin.read(b)) != -1) {
					outs.write(b, 0, read);
				}
				*/
				
				FileInputStream fin = new FileInputStream(file); 	// 파일읽기 객체(스트림)
				OutputStream outs = res.getOutputStream();			// 웹브라우저에 보내는 응답객체의 출력 스트림 객체
						
				byte[] buffer = new byte[DOWNLOAD_BUFF_SIZE]; // 8KB
				
				while(true) {
					int readlen = fin.read(buffer);
					// System.out.println("read : len=" + readlen);
					if(readlen < 0) {
						break;
					}
					
					outs.write(buffer, 0, readlen);
				}
				
				outs.close();
				fin.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 파일 다운로드(이미지)
	public void downImage(HttpServletRequest req, HttpServletResponse res, JspWriter out, PageContext pageContext) {
		try {
			req.setCharacterEncoding("UTF-8");

			String filename = req.getParameter("filename");
			
			String fullpath = SAVEFOLDER + File.separator + filename;
			System.out.println("[download] fullpath="+ fullpath);
			
			File file = new File(fullpath);
			
			System.out.println("[download] filesize= "+ file.length());
			
			if (file.isFile()) {
				FileInputStream fin = new FileInputStream(file); 	// 파일읽기 객체(스트림)
				OutputStream outs = res.getOutputStream();			// 웹브라우저에 보내는 응답객체의 출력 스트림 객체
						
				byte[] buffer = new byte[DOWNLOAD_BUFF_SIZE]; // 8KB
				
				while(true) {
					int readlen = fin.read(buffer);
					// System.out.println("read : len=" + readlen);
					if(readlen < 0) {
						break;
					}
					
					outs.write(buffer, 0, readlen);
				}
				
				outs.close();
				fin.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		
	// 페이징 및 블럭 테스트를 위한 게시물 저장 메소드 
	public void post1000() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert into boards (num,name,content,subject,ref,pos,depth,regdate,pass,count,ip,filename,filesize) ";
			sql+= "values(boards_seq.nextval, 'aaa', 'bbb', 'ccc', 0, 0, 0, sysdate, '1111', 0, '127.0.0.1', null, 0)";
			
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < 1000; i++) {
				pstmt.executeUpdate();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	// main
	public static void main(String[] args) {
		new BoardDAO().post1000();
		System.out.println("SUCCESS");
	}
}