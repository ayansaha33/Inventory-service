
$(document).ready(function () {

    $("#product-chart-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();
        
		$( "#holder" ).empty();
		$( "#chartdraw" ).empty();
		$( "#dynamic-data" ).empty();

        fire_ajax_submit();
		//populatechart();

    });

});

function fire_ajax_submit() {

    $.ajax({
        url: "/api/product/client/getAllProducts",
        //url:"http://localhost:8080/getSuccessBooking/fromSource",
        success: function (data) {

			
    for(var i in data){
        
		$('<tr>').html("<td>" + data[i].id + "</td><td>" + data[i].productname + "</td><td>" + data[i].productcategory + "</td><td>" + data[i].productPrice + "</td>").appendTo('#records-table');
    }

            console.log("SUCCESS : ", data);


        },
        error: function (e) {

            var json = "<h3>Ajax Response</h3>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#holder').html(json);

            console.log("ERROR : ", e);

        }
    });

}

