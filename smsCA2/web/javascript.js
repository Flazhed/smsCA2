/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    //The filter function
    $("#searchInput2").keyup(function () {
        $("#test").html($("#searchInput2").val());
    });

    //live search
    $("#searchInput").keyup(function () {

        $("#spinner").show();

        if ($("#searchInput").val() === "") {
            $("#spinner").hide();
        } else {
            liveSearch();
        }


    });

    //Send the a request
    $("#sendReq").click(getMockData);

    $("#addperson").click(function (){
        $("#personInput").hide();
        $("#succecSubmit").show();
        $('#succecSubmit').delay(2000).fadeOut();

       // for implementation later error info.
       // $("#failSubmit").show();
       //$('#failSubmit').delay(2000).fadeOut();
    });



    //Hide the input fields from the user upon startup.
    $("#personInput").hide();
    $("#companyInput").hide();
    $("#spinner").hide();
    $("#failSubmit").hide();
    $("#succecSubmit").hide();

    //for later implementation. 
//   $("#tableData").hide();

    // logic for the dropdown menu
    $('.dropdown-toggle').dropdown();

    //Click events for the input fields
    $("#createPerson").click(function () {
        $("#personInput").toggle();
    });
    $("#createCompany").click(function () {
        $("#companyInput").toggle();
    });
});


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
        for (var i = 0; i < json.length; i++) {

            $("#tableData").append("<tr><td><button id='edit" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
            "</span>  Edit </button></td>" + "<td>" + json[i].first_name + "</td>" + "<td>" + json[i].last_name + "</td>" + "<td>" +
            json[i].email + "</td></tr>");

            $("#edit" + i).data(json[i]);
            $("#edit" + i).click(editPerson);
        }
    });


}

// TO be Added when stefan los slowmo is done with the fucking api!!!!!!!


//Function for edit a person, auto fill inputfields

function editPerson() {

    var obj = $(this).data();

    $("#personInput").show();

    $('#inputPersonName').val(obj.first_name);
    $('#inputLastname').val(obj.last_name);
    $('#inputPersonEmail').val(obj.email);
    $('#inputPhone').val(obj.country);

}


//Filter function for table -------------

var data = [];
function filterData(elem) {

    var bool = elem.sName.search($("#fiName").val()) && elem.sCountryName.search($("#fiName").val());
    if (bool === -1) {
        return false;
    }

    return true;
}

function filterPlayer() {

    var filteredPlayers = arrPlayers.filter(filterPerson);
}

//-------------------------------------
