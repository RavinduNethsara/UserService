//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidUserIDSave").val("");
	$("#USER")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#userID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "UserAPI",
		type : type,
		data : $("#USER").serialize(),
		dataType : "text",
		complete : function(response, status) {
			//console.log(status);
			onItemSaveComplete(response.responseText, status);
			window.location.reload(true);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#UserGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#userID").val("");
	$("#USER")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "UserAPI",
		type : "DELETE",
		data : "userID=" + event.target.value,
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
			window.location.reload(true);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#UserGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#userID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#userType").val($(this).closest("tr").find('td:eq(1)').text());
			$("#userName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#userEmail").val($(this).closest("tr").find('td:eq(3)').text());
			$("#userPassword").val($(this).closest("tr").find('td:eq(4)').text());
			$("#userPhone").val($(this).closest("tr").find('td:eq(5)').text());
			$("#userAddress").val($(this).closest("tr").find('td:eq(6)').text());	
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// User Type
	if ($("#userType").val().trim() == "") {
		return "Please insert user type.";
	}
	
	// User Name
	if ($("#userName").val().trim() == "") {
		return "Please insert user name.";
	}
	
	// Email
	if ($("#userEmail").val().trim() == "") {
		return "Please insert Email.";
	}
	
	// Password
	if ($("#userPassword").val().trim() == "") {
		return "Please insert user password.";
	}
	
	
	// Phone
	if ($("#userPhone").val().trim() == "") {
		return "Please insert user phone.";
	}
	
	// Address
	if ($("#userAddress").val().trim() == "") {
		return "Please insert user Address.";
	}
	
	
	return true;
}
