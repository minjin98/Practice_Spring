[자바 입출력]

1. IO(Input & Output)
   - 방향성: 단방향, 양방향
   - 목적지: 파일시스템, 네트워크, 프린터, 모니터, 키보드
   - 로우레벨: 바이트단위 입출력이 처리
   - 단방향: 프린터(output), 키보드(input), 모니터(output)
   - 양방향: HDD, 이더넷카드

2. 스트림(Stream)
   - 물이 흐르는 통로: 시내, 흐름
   - 연속적으로 데이터 이동 처리
   - 동영상 스트리밍
      
3. 자바에서 스트림
   - 바이트 기반 스트림
     -> 그림, 멀티미디어
     -> 문자 등 모든 종류의 데이터를 받고 보낼 수 있다.
   - 문자 기반 스트림
     -> 문자만 보내고 받을 수 있다.

4. java.io 패키지

5. 바이트 기반 스트림
   - 입력 스트림: InputStream
   - 출력 스트림: OutputStream
   
6. 문자 기반 스트림
   - 입력 스트림: Reader
   - 출력 스트림: Writer 
   
7. IO 메커니즘
   - 열다: open
   - 읽다: read
   - 쓰다: write
   - 닫다: close
   
8. 입력 스트림(InputStream)
   - FileInputStream
   - BufferedInputStream
   - DataInputStream
   - ObjectInputStream

9. 출력 스트림(OutputStream)
   - FileOutputStream
   - PrintStream
   - BufferedOutputStream
   - DataOutputStream 
   - ObjectOutputStream        
   
10. 문자기반 입력 스트림(Reader)
   - FileReader
   - BufferedReader
   - InputStreamReader
   
11. 문자기반 출력 스트림(Writer)
   - FileWriter
   - BufferedWriter
   - PrintWriter
   - OutputStreamWriter   
   
12. 기타
   - File : 파일시스템 정보
   - Console : 콘솔 입출력
       
