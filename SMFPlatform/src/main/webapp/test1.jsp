<!DOCTYPE html>
<html lang="en">
<head>
    <title id="Description">Data Rows with Details in jqxDataTable</title>
    <meta name="description" content="This sample demonstrates how we can display Rows with Details in the jQWidgets DataTable widget.">
    <link rel="stylesheet" href="/jqwidgets/jqwidgets/styles/jqx.base.css" type="text/css" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1" />	
    <script type="text/javascript" src="/jqwidgets/scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/jqwidgets/scripts/demos.js"></script>
    <script type="text/javascript" src="/jqwidgets/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="/jqwidgets/jqwidgets/jqxdata.js"></script> 
    <script type="text/javascript" src="/jqwidgets/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="/jqwidgets/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="/jqwidgets/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="/jqwidgets/jqwidgets/jqxdatatable.js"></script>
    <script type="text/javascript" src="/jqwidgets/jqwidgets/jqxtabs.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // prepare the data
            var data = new Array();
            var firstNames = ["KBM", "Andrew", "Janet", "Margaret", "Steven", "Michael", "Robert", "Laura", "Anne"];
            var lastNames = ["Davolio", "Fuller", "Leverling", "Peacock", "Buchanan", "Suyama", "King", "Callahan", "Dodsworth"];
            var titles = ["Sales Representative", "Vice President, Sales", "Sales Representative", "Sales Representative", "Sales Manager", "Sales Representative", "Sales Representative", "Inside Sales Coordinator", "Sales Representative"];
            var titleofcourtesy = ["Ms.", "Dr.", "Ms.", "Mrs.", "Mr.", "Mr.", "Mr.", "Ms.", "Ms."];
            var birthdate = ["08-Dec-48", "19-Feb-52", "30-Aug-63", "19-Sep-37", "04-Mar-55", "02-Jul-63", "29-May-60", "09-Jan-58", "27-Jan-66"];
            var hiredate = ["01-May-92", "14-Aug-92", "01-Apr-92", "03-May-93", "17-Oct-93", "17-Oct-93", "02-Jan-94", "05-Mar-94", "15-Nov-94"];
            var address = ["507 - 20th Ave. E. Apt. 2A", "908 W. Capital Way", "722 Moss Bay Blvd.", "4110 Old Redmond Rd.", "14 Garrett Hill", "Coventry House", "Miner Rd.", "Edgeham Hollow", "Winchester Way", "4726 - 11th Ave. N.E.", "7 Houndstooth Rd."];
            var city = ["Seattle", "Tacoma", "Kirkland", "Redmond", "London", "London", "London", "Seattle", "London"];
            var postalcode = ["98122", "98401", "98033", "98052", "SW1 8JR", "EC2 7JR", "RG1 9SP", "98105", "WG2 7LT"];
            var country = ["USA", "USA", "USA", "USA", "UK", "UK", "UK", "USA", "UK"];
            var homephone = ["(206) 555-9857", "(206) 555-9482", "(206) 555-3412", "(206) 555-8122", "(71) 555-4848", "(71) 555-7773", "(71) 555-5598", "(206) 555-1189", "(71) 555-4444"];
           
            var k = 0;
            for (var i = 0; i < firstNames.length; i++) {
                var row = {};
                row["firstname"] = firstNames[k];
                row["lastname"] = lastNames[k];
                row["title"] = titles[k];
                row["titleofcourtesy"] = titleofcourtesy[k];
                row["birthdate"] = birthdate[k];
                row["hiredate"] = hiredate[k];
                row["address"] = address[k];
                row["city"] = city[k];
                row["postalcode"] = postalcode[k];
                row["country"] = country[k];
                row["homephone"] = homephone[k];
                data[i] = row;
                k++;
            }
            var source =
            {
                localData: data,
                dataType: "array"
            };
            // initialize the row details.
            var initRowDetails = function (id, row, element, rowinfo) {
                var tabsdiv = null;
                var information = null;
                // update the details height.
                rowinfo.detailsHeight = 200;
                element.append($("<div style='margin: 10px;'><ul style='margin-left: 30px;'><li class='title'>Title</li></ul><div class='information'></div></div>"));
                tabsdiv = $(element.children()[0]);
             
                if (tabsdiv != null) {
                    information = tabsdiv.find('.information');
                    var title = tabsdiv.find('.title');
                    title.text(row.firstname);
                    var container = $('<div style="margin: 5px;"></div>')
                    container.appendTo($(information));
                    var photocolumn = $('<div style="float: left; width: 15%;"></div>');
                    var leftcolumn = $('<div style="float: left; width: 45%;"></div>');
                    var rightcolumn = $('<div style="float: left; width: 40%;"></div>');
                    container.append(photocolumn);
                    container.append(leftcolumn);
                    container.append(rightcolumn);
                    var photo = $("<div class='jqx-rc-all' style='margin: 10px;'><b>Photo:</b></div>");
                    var image = $("<div style='margin-top: 10px;'></div>");
                    var imgurl = '/jqwidgets/images/' + row.firstname.toLowerCase() + '.png';
                    var img = $('<img height="60" src="' + imgurl + '"/>');
                    image.append(img);
                    image.appendTo(photo);
                    photocolumn.append(photo);
                    var firstname = "<div style='margin: 10px;'><b>First Name:</b> " + row.firstname + "</div>";
                    var lastname = "<div style='margin: 10px;'><b>Last Name:</b> " + row.lastname + "</div>";
                    var title = "<div style='margin: 10px;'><b>Title:</b> " + row.title + "</div>";
                    var address = "<div style='margin: 10px;'><b>Address:</b> " + row.address + "</div>";
                    $(leftcolumn).append(firstname);
                    $(leftcolumn).append(lastname);
                    $(leftcolumn).append(title);
                    $(leftcolumn).append(address);
                    var postalcode = "<div style='margin: 10px;'><b>Postal Code:</b> " + row.postalcode + "</div>";
                    var city = "<div style='margin: 10px;'><b>City:</b> " + row.city + "</div>";
                    var phone = "<div style='margin: 10px;'><b>Phone:</b> " + row.homephone + "</div>";
                    var hiredate = "<div style='margin: 10px;'><b>Hire Date:</b> " + row.hiredate + "</div>";
                    $(rightcolumn).append(postalcode);
                    $(rightcolumn).append(city);
                    $(rightcolumn).append(phone);
                    $(rightcolumn).append(hiredate);
                    $(tabsdiv).jqxTabs({ width: 820, height: 170 });
                }
            }
            var dataAdapter = new $.jqx.dataAdapter(source);
            $("#dataTable").jqxDataTable(
            {
                width: getWidth("dataTable"),
                source: dataAdapter,
                pageable: true,
                pageSize: 3,
                rowDetails: true,
                sortable: true,
                ready: function () {
                    // expand the first details.
                    $("#dataTable").jqxDataTable('showDetails', 0);
                },
                initRowDetails: initRowDetails,
                columns: [
                      { text: 'First Name', dataField: 'firstname', width: 200 },
                      { text: 'Last Name', dataField: 'lastname', width: 200 },
                      { text: 'Title', dataField: 'title', width: 200 },
                      { text: 'City', dataField: 'city', width: 100 },
                      { text: 'Country', dataField: 'country'}
                ]
            });
        });
    </script>
</head>
<body class='default'>
      <div id="dataTable"></div>
</body>
</html>