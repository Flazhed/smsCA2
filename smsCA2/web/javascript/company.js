/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {


    $("#spinner1").hide();
    $("#failSubmit1").hide();
    $("#succecSubmit1").hide();
    $("#deletedINFO1").hide();

    $("#getAllCompanies").click(function () {

        getAllCompanies();

    });

    $("#sendReqCompanies").click(function () {

        var dataToSend = $("#searchInput1").val();

        console.log(dataToSend);

        if ($('#CompanyCvr').prop('checked')) {
            getCompanyCVR(dataToSend);
        }
        else if ($('#CompanyName').prop('checked')) {
            getCompanyName(dataToSend);
        }
        else if ($('#countEmploye').prop('checked')){
            companyEmpCount(dataToSend);
        }


    });

});




function getCompanyCVR(data) {

    console.log(data);

    $("#spinner").show();

    $.ajax({
        type: "GET",
        url: "api/company/complete/" + data,
        // The key needs to match your method's input parameter (case-sensitive).
        dataType: "json",
        success: function (json) {

            console.log(json);

            if (json === null) {
                return;
            } else
                $("#spinner1").hide();

            //console.log("json obj: " + json);

         $("#tableData1").html("<thead><th>edit:</th><th>Name:</th><th>Email:</th><th>CVR:</th></thead>");

//            for (var i = 0; i < json.length; i++) {
            $("#tableData1").append("<tr><td><button id='editc" + 0 + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                    "</span>  Edit </button></td>" + "<td>" + json.name + "</td>" + "<td>" + json.email + "</td>" + "<td>" +
                    json.cvr + "</td></tr>");
            $("#editc" + 0).data(json[0]);
            $("#editc" + 0).click(editPerson);
//            }

        },
        failure: function (errMsg) {
            $("#failSubmit").show();
            $('#failSubmit').delay(2500).fadeOut();
            alert(errMsg);
        }
    });

}

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



function getCompanyName(data) {

    console.log("in here");

    $("#spinner1").show();

    var request = $.ajax({
        url: "api/company/find/" + data,
        type: "GET",
        dataType: "json"
    });
    request.done(function (json) {

        console.log(json);

        if (json === null) {
            return;
        } else
//            dataFilter = json;

            $("#spinner1").hide();

        //console.log("json obj: " + json);

$("#tableData1").html("<thead><th>edit:</th><th>Name:</th><th>Email:</th><th>CVR:</th></thead>");

        for (var i = 0; i < json.length; i++) {
            $("#tableData1").append("<tr><td><button id='editc" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                    "</span>  Edit </button></td>" + "<td>" + json[i].name + "</td>" + "<td>" + json[i].email + "</td>" + "<td>" +
                    json[i].cvr + "</td></tr>");
            $("#editc" + i).data(json[i]);
            $("#editc" + i).click(editPerson);
        }
    });
    request.fail(function (data) {
        $("#spinner").hide();
        console.log(data.statusText);
    });



}

function getAllCompanies() {

    console.log("in here");

    $("#spinner1").show();

    var request = $.ajax({
        url: "api/company/complete",
        type: "GET",
        dataType: "json"
    });
    request.done(function (json) {

        console.log(json);

        if (json === null) {
            return;
        } else
//            dataFilter = json;

            $("#spinner1").hide();

        //console.log("json obj: " + json);

        $("#tableData1").html("<thead><th>edit:</th><th>Name:</th><th>Email:</th><th>CVR:</th></thead>");

        for (var i = 0; i < json.length; i++) {
            $("#tableData1").append("<tr><td><button id='editc" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                    "</span>  Edit </button></td>" + "<td>" + json[i].name + "</td>" + "<td>" + json[i].email + "</td>" + "<td>" +
                    json[i].cvr + "</td></tr>");
            $("#editc" + i).data(json[i]);
            $("#editc" + i).click(editPerson);
        }
    });
    request.fail(function (data) {
        $("#spinner").hide();
        console.log(data.statusText);
    });

}


function companyEmpCount(data) {

console.log("here");

    var request = $.ajax({
        url: "api/company/companyEmpCount/" + data,
        type: "GET",
        dataType: "json"
    });
    request.done(function (data) {
        if (data === null) {
            return;
        } else
            $("#spinner").hide();

        console.log("json obj: " + data);


        $("#spinner1").hide();

        //console.log("json obj: " + json);

    $("#tableData1").html("<thead><th>edit:</th><th>Name:</th><th>Email:</th><th>CVR:</th></thead>");

        for (var i = 0; i < data.length; i++) {
            $("#tableData1").append("<tr><td><button id='editc" + i + "'" + " class='btn'><span class='glyphicon glyphicon-pencil'>" +
                    "</span>  Edit </button></td>" + "<td>" + data[i].name + "</td>" + "<td>" + data[i].email + "</td>" + "<td>" +
                    data[i].cvr + "</td></tr>");
            $("#editc" + i).data(data[i]);
            $("#editc" + i).click(editPerson);
        }

    });
    request.fail(function (data) {
        $("#spinner").hide();
        console.log(data.statusText);
    });


}
    