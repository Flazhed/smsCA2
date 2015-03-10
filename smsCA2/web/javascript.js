/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("#searchInput2").keyup(function () {
        $("#test").html($("#searchInput2").val());
    });

    $("#sendReq").click(getSingleEntity);

    $("#personInput").hide();

    //for later implementation. 
//   $("#tableData").hide();

//    $("#sendReq").click(function () {
//        $("#personInput").toggle();
//    });

});

function getSingleEntity() {

 $("#tableInsertion").html("<td>"+"data.firstName"+"</td>");

// to be added!
//    var request = $.ajax({
//        url: "api/person/complete/" + $("#searchInput").val(),
//        type: "GET",
//        dataType: "json"
//    });
//    request.done(function (data) {
//        $("#tableInsertion").html("<td>"+data.firstName+"</td>");
//    });


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
