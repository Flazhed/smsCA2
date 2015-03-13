/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
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

        }
        else if ($('#EmpCheckbox').prop('checked')) {

        }


    });

    $("#deletePerson").click(deletePerson);

    $("#addperson").click(function () {
        addPerson();
        $("#personInput").hide();
    });

    $("#editPerson").click(function () {
        editPersonDB();
        $("#personInput").hide();
    });


    //Hide the input fields from the user upon startup.
    $("#personInput").hide();
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
    $("#createPerson").click(function () {
        //clear fields - should be a smarter way.
        $("#inputPersonName").val("");
        $("#inputLastname").val("");
        $("#inputPersonEmail").val("");

        $("#personInput").toggle();
        $("#editPerson").hide();
        $("#addperson").show();
    });
    $("#createCompany").click(function () {
        $("#companyInput").toggle();
    });
});


//----------------------------- PERSON ---------------------------------//

function getEntityByPhone(data) {

    $("#spinner").show();

    $.ajax({
        type: "POST",
        url: "api/person/find",
        // The key needs to match your method's input parameter (case-sensitive).
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (json) {

            if (json === null) {
                return;
            } else
                $("#spinner").hide();

            //console.log("json obj: " + json);

            $("#tableData").html("");

            for (var i = 0; i < json.length; i++) {
                $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                        "</span>  Edit </button></td>" + "<td>" + json[i].firstName + "</td>" + "<td>" + json[i].lastName + "</td>" + "<td>" +
                        json[i].email + "</td></tr>");
                $("#edit" + i).data(json[i]);
                $("#edit" + i).click(editPerson);
            }

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
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (json) {

            dataFilter = json;

            if (json === null) {
                return;
            } else
                $("#spinner").hide();

            //console.log("json obj: " + json);

            $("#tableData").html("");

            for (var i = 0; i < json.length; i++) {
                $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                        "</span>  Edit </button></td>" + "<td>" + json[i].firstName + "</td>" + "<td>" + json[i].lastName + "</td>" + "<td>" +
                        json[i].email + "</td></tr>");
                $("#edit" + i).data(json[i]);
                $("#edit" + i).click(editPerson);
            }

        },
        failure: function (errMsg) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            alert(errMsg);
        }
    });

}


function deletePerson() {

    var person = editP;

    $.ajax({
        url: 'api/person',
        type: 'DELETE',
        dataType: 'json',
        data: person,
        success: function (data, textStatus, xhr) {
            $("#deletedINFO").show();
            $('#deletedINFO').delay(2500).fadeOut();
            console.log(data);

        },
        error: function (xhr, textStatus, errorThrown) {
            console.log('Error in Operation');
        }
    });

}

function editPersonDB() {

    editP.firstName = $("#inputPersonName").val();
    editP.lastName = $("#inputLastname").val();
    editP.email = $("#inputPersonEmail").val();


    console.log(editP);

    // to be added phone array -- phones: [$("#").val()]

    $.ajax({
        type: "PUT",
        url: "api/person",
        // The key needs to match your method's input parameter (case-sensitive).
        data: JSON.stringify(editP),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#succecSubmit").show();
            $('#succecSubmit').delay(2500).fadeOut();
            getPersons();
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

        //console.log("json obj: " + json);

        $("#tableData").html("");

        for (var i = 0; i < json.length; i++) {
            $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                    "</span>  Edit </button></td>" + "<td>" + json[i].firstName + "</td>" + "<td>" + json[i].lastName + "</td>" + "<td>" +
                    json[i].email + "</td></tr>");
            $("#edit" + i).data(json[i]);
            $("#edit" + i).click(editPerson);
        }
    });
    request.fail(function (data) {
        $("#spinner").hide();
        console.log(data.statusText);
    });

}

//Add persons

function addPerson() {

    var person = {firstName: $("#inputPersonName").val(), lastName: $("#inputLastname").val()
        , email: $("#inputPersonEmail").val()};

    // to be added phone array -- phones: [$("#").val()]

    console.log(person);

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
            console.log("added " + data);
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

        console.log("json obj: " + json);

        $("#tableData").html("<tr><td><button id='edit" + 1 + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                "</span>  Edit </button></td>" + "<td>" + json.firstName + "</td>" + "<td>" + json.lastName + "</td>" + "<td>" +
                json.email + "</td></tr>");

    });
    request.fail(function (data) {
        $("#spinner").hide();
        console.log(data.statusText);
    });


}

//Function for edit a person, auto fill inputfields

var editP = {};

function editPerson() {

    $("#editPerson").show();
    $("#addperson").hide();

    var obj = $(this).data();

    editP = obj;

    $("#personInput").toggle();

    $('#inputPersonName').val(obj.firstName);
    $('#inputLastname').val(obj.lastName);
    $('#inputPersonEmail').val(obj.email);
//    $('#inputPhone').val(obj.phones[0]);

}

// MOCK DATA SETUP

function getMockData() {

    $.getJSON("MOCK_DATA.json", function (json) {
//        console.log("JSON Data: " + json[1].first_name);

        for (var i = 0; i < json.length; i++) {

            $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                    "</span>  Edit </button></td>" + "<td>" + json[i].first_name + "</td>" + "<td>" + json[i].last_name + "</td>" + "<td>" +
                    json[i].email + "</td></tr>");

            $("#edit" + i).data(json[i]);
            $("#edit" + i).click(editPerson
                    );
        }

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

    $("#tableData").html("");

    for (var i = 0; i < json.length; i++) {
        $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                "</span>  Edit </button></td>" + "<td>" + json[i].firstName + "</td>" + "<td>" + json[i].lastName + "</td>" + "<td>" +
                json[i].email + "</td></tr>");
        $("#edit" + i).data(json[i]);
        $("#edit" + i).click(editPerson);
    }


}

//-------------------------------------
