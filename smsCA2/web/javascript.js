/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {



    $('#myTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });


    //The filter function
    $("#searchInput2").keyup(filterPerson);

    //live search
    /*
     $("#searchInput").keyup(function () {
     
     $("#spinner").show();
     
     if ($("#searchInput").val() === "") {
     $("#spinner").hide();
     } else {
     liveSearch();
     }
     });
     */
    //Send the a request
    $("#sendReq").click(function () {

        var dataToSend = $("#searchInput").val();

        if ($('#NamesCheckbox').prop('checked')) {
            getPersonsByNames(dataToSend);
        }
        else if ($('#PhoneCheckbox').prop('checked')) {
            getEntityByPhone(dataToSend);
        }
        else if ($('#zipCodeBox').prop('checked')) {
            getPersonByZip(dataToSend);
        }
        else if ($("#hobbyBox").prop('checked')) {
            getPersonByHobby(37);
        }

    });

    $("#getAll").click(getPersons);

    $("#deletePerson").click(deletePerson);

    $("#addperson").click(function () {
        addPerson();
    });

    $("#editPerson").click(function () {
        editPersonDB();
    });


    //Hide the input fields from the user upon startup.
//    $("#personInput").hide();
    $("#companyInput").hide();
    $("#spinner").hide();
    $("#failSubmit").hide();
    $("#succecSubmit").hide();
    $("#deletedINFO").hide();

    //for later implementation. 
//   $("#tableData").hide();

    // logic for the dropdown menu
    $('.dropdown-toggle').dropdown();

    //Click events for the input fields
    $("#createPerson").click('shown.bs.modal', function () {
        $("#inputPersonName").val("");
        $("#inputLastname").val("");
        $("#inputPersonEmail").val("");
        $('#inputPhone').val("");
        $('#inputHobby').val("");
        $("#editPerson").hide();
        $("#addperson").show();
        $('#myInput').focus();
    });




    $("#createCompany").click(function () {
        $("#companyInput").toggle();
    });
});

var editP;
//----------------------------- PERSON ---------------------------------//

function tableSetupIfArray(json) {




    $("#tableData").html("<thead><th>edit:</th><th>Firstname:</th><th>Lastname:</th><th>PhoneNo:</th><th>email:</th></thead>");

    for (var i = 0; i < json.length; i++) {

        var temp = json[i].phones[0];

        if (temp === undefined) {
            $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn' data-toggle='modal' data-target='#myModal'><span class='glyphicon glyphicon-pencil' class='btn btn-primary btn-lg'>" +
                    "</span>  Edit </button></td>" + "<td>" + json[i].firstName + "</td>" + "<td>" + json[i].lastName + "</td>" + "<td>" +
                    "no number yet" + "</td>" + "<td>" +
                    json[i].email + "</td></tr>");
        }

        else {
            $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn' data-toggle='modal' data-target='#myModal'><span class='glyphicon glyphicon-pencil' class='btn btn-primary btn-lg'>" +
                    "</span>  Edit </button></td>" + "<td>" + json[i].firstName + "</td>" + "<td>" + json[i].lastName + "</td>" + "<td>" +
                    json[i].phones[0].phoneNumber + "</td>" + "<td>" +
                    json[i].email + "</td></tr>");
        }
        $("#edit" + i).data(json[i]);
        $("#edit" + i).click(editItPlez);
//        $("#edit" + i).click(editPerson);
        $("#edit" + i).click('shown.bs.modal', function () {
            $('#myInput').focus();
        });
    }

}


function editItPlez() {

    $("#inputPersonName").val("");
    $("#inputLastname").val("");
    $("#inputPersonEmail").val("");
    $('#inputPhone').val("");
    $('#inputHobby').val("");


    $("#editPerson").show();
    $("#addperson").hide();

    var obj = $(this).data();

    editP = obj;
//
//    $("#personInput").toggle();

//    $("#myModal").toggle();




    $('#inputPersonName').val(obj.firstName);
    $('#inputLastname').val(obj.lastName);
    $('#inputPersonEmail').val(obj.email);
    $('#inputPhone').val(obj.phones[0].phoneNumber);
    $('#inputHobby').val(obj.hobbies[0].name);


}

function getPersonByHobby(data) {


    $("#spinner").show();

    $.ajax({
        type: "GET",
        url: "api/person/getPersonsByHobby/" + data,
        // The key needs to match your method's input parameter (case-sensitive).
        dataType: "json",
        success: function (json) {

            if (json === null) {
                return;
            } else
                $("#spinner").hide();

            tableSetupIfArray(json);

        },
        failure: function (errMsg) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            alert(errMsg);
        }
    });

}



function getEntityByPhone(data) {


    $("#spinner").show();

    $.ajax({
        type: "POST",
        url: "api/person/phone/find",
        // The key needs to match your method's input parameter (case-sensitive).
        data: data,
        contentType: "text/plain; charset=utf-8",
        dataType: "json",
        success: function (json) {

            if (json === null) {
                return;
            } else
                $("#spinner").hide();

            $("#tableData").html("<thead><th>edit:</th><th>Firstname:</th><th>Lastname:</th><th>email:</th></thead>");

//            for (var i = 0; i < json.length; i++) {
            $("#tableData").append("<tr><td><button id='edit" + 0 + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                    "</span>  Edit </button></td>" + "<td>" + json.firstName + "</td>" + "<td>" + json.lastName + "</td>" + "<td>" +
                    json.email + "</td></tr>");
            $("#edit" + 0).data(json);
            $("#edit" + 0).click(editItPlez);
//            }

        },
        failure: function (errMsg) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            alert(errMsg);
        }
    });

}

function getPersonByZip(data) {


    $("#spinner").show();

    $.ajax({
        type: "GET",
        url: "api/person/zipCode/" + data,
        // The key needs to match your method's input parameter (case-sensitive).
        dataType: "json",
        success: function (json) {

            if (json === null) {
                return;
            } else
                $("#spinner").hide();

            tableSetupIfArray(json);

        },
        failure: function (errMsg) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            alert(errMsg);
        }
    });

}

function getPersonsByNames(data) {

    $("#spinner").show();

    $.ajax({
        type: "POST",
        url: "api/person/find",
        // The key needs to match your method's input parameter (case-sensitive).
        data: data,
        contentType: "text/plain; charset=utf-8",
        dataType: "json",
        success: function (json) {

        console.log("succ");

            dataFilter = json;

            if (json === null) {
                return;
            } else
                $("#spinner").hide();

            //console.log("json obj: " + json);

            tableSetupIfArray(json);

        },
        error: function (error) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            console.log(error);
        }
    });

}


function deletePerson() {

    var person = editP;


    $.ajax({
        url: 'api/person',
        type: "DELETE",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(person),
        success: function () {
            $("#deletedINFO").show();
            $('#deletedINFO').delay(2500).fadeOut();
        },
        error: function () {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
        }
    });

}

function editPersonDB() {
  console.log("her");
    editP.firstName = $("#inputPersonName").val();
    editP.lastName = $("#inputLastname").val();
    editP.email = $("#inputPersonEmail").val();


    console.log(JSON.stringify(editP));


    // to be added phone array -- phones: [$("#").val()]

    $.ajax({
        type: "PUT",
        url: "api/person",
        // The key needs to match your method's input parameter (case-sensitive).
        data: JSON.stringify(editP),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            $("#succecSubmit").show();
            $('#succecSubmit').delay(2500).fadeOut();
//            getPersons();
//console.log(data);
        },
        failure: function (errMsg) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            alert(errMsg);
        }
    });

}

// Get all persons
function getPersons() {


    $("#spinner").show();

    var request = $.ajax({
        url: "api/person/complete",
        type: "GET",
        dataType: "json"
    });
    request.done(function (json) {

        if (json === null) {
            return;
        } else
            dataFilter = json;

        $("#spinner").hide();


        tableSetupIfArray(json);
    });
    request.fail(function (error) {
        $("#spinner").hide();
        console.log(error);
        
    });

}

//Add persons

function addPerson() {

    var person = {firstName: $("#inputPersonName").val(), lastName: $("#inputLastname").val()
        , email: $("#inputPersonEmail").val(), phones: $('#inputPhone').val()};

    // to be added phone array -- phones: [$("#").val()]


    $.ajax({
        type: "POST",
        url: "api/person",
        // The key needs to match your method's input parameter (case-sensitive).
        data: JSON.stringify(person),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            $("#succecSubmit").show();
            $('#succecSubmit').delay(2500).fadeOut();
        },
        failure: function (errMsg) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            alert(errMsg);
        }
    });



}


//Function for liveSearch

function liveSearch() {

    var request = $.ajax({
        url: "api/person/complete/" + $("#searchInput").val(),
        type: "GET",
        dataType: "json"
    });
    request.done(function (json) {
        if (json === null) {
            return;
        } else
            $("#spinner").hide();




        $("#tableData").html("<tr><td><button id='edit" + 1 + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                "</span>  Edit </button></td>" + "<td>" + json.firstName + "</td>" + "<td>" + json.lastName + "</td>" + "<td>" +
                json.email + "</td></tr>");

    });
    request.fail(function (data) {
        $("#spinner").hide();
    });


}

//Function for edit a person, auto fill inputfields



function editPerson() {


    $("#editPerson").show();
    $("#addperson").hide();

    var obj = $(this).data();

    editP = obj;

    $("#personInput").toggle();

//    $("#myModal").toggle();


    $('#inputPersonName').val(obj.firstName);
    $('#inputLastname').val(obj.lastName);
    $('#inputPersonEmail').val(obj.email);
    $('#oleole').val(79879);

}

// MOCK DATA SETUP

function getMockData() {

    $.getJSON("MOCK_DATA.json", function (json) {
//        console.log("JSON Data: " + json[1].first_name);

        tableSetupIfArray();

    });

}

//--------------------------- PERSON  END ---------------------------------//

//Filter function for table -------------

var dataFilter;

function filterData(elem) {

    console.log(elem.firstName);

    var firstNameLower = elem.firstName.toLowerCase();

    var searchLower = $("#searchInput2").val().toLowerCase();

    var bool = firstNameLower.search(searchLower);
    console.log(bool);
    if (bool === -1) {
        return false;
    }

    return true;
}

function filterPerson() {

    var json = dataFilter.filter(filterData);

    console.log(json);

    tableSetupIfArray(json);


}

//-------------------------------------
