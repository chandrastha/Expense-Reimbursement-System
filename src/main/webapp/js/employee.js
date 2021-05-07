/**
 * 
 */

 window.onload = function(){
	 getSessUser();
	 getAllReimbRequestByUserID();
	window.history.forward();
    function noBack() { window.history.forward(); }
 }

 function getSessUser() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function () {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let user = JSON.parse(xhttp.responseText);
			//  console.log(user);
			//  console.log(xhttp.responseText);
			document.getElementById("welcome").innerText = `Welcome ${user.firstname}`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ProjectOne/getsessionuser.json");
	xhttp.send();
}
function getAllReimbRequestByUserID(){
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function(){
		if (xhttp.readyState == 4 && xhttp.status == 200){
			let reimbRequestsById = JSON.parse(xhttp.responseText);
            // console.log("output is " + xhttp.responseText);
			console.log("inside get reimb request by ID");
			domManipulationEmp(reimbRequestsById);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProjectOne/employee/getreimbursements.json");                                            
	xhttp.send();
}

function addReimbRequest(){
	let xhttp = new XMLHttpRequest();
	let newRequest = {
		re_amount: document.getElementById("amount").value,
		re_typeid: document.getElementById("type").value,
		re_description: document.getElementById("description").value
	}

	if(newRequest.amount <= 0 || isNaN(newRequest.re_amount)){
		alert("Please Enter Valid Amount");
		formReset();
	}
	else{
		let newReimbFormString = JSON.stringify(newRequest);
		// console.log("Form value is ::::"+ newReimbFormString);
		let url = "http://localhost:8080/ProjectOne/employee/newreimbursement.json"
		xhttp.open("POST", url);
		xhttp.send(newReimbFormString);
		xhttp.onreadystatechange = function(){
			if(xhttp.readyState == 4 && xhttp.status == 200){
			document.getElementById("reimbTableForEmployee").getElementsByTagName("tbody")[0].innerHTML = "";
			getAllReimbRequestByUserID();
			formReset();
		}
		}
	}
}

function formReset(){
	let form = document.getElementById("reimbForm");
	form.reset();
}
function domManipulationEmp(reimbRequestsById){
	reimbRequestsById.forEach(element => {
		console.log("inside employee dom manipulation");
		let table = document.getElementById("reimbTableForEmployee").getElementsByTagName("tbody")[0];
		let row = document.createElement("tr");
		let id = document.createElement("td");
		let amount = document.createElement("td");
		let submitted = document.createElement("td");
		let resolved = document.createElement("td");
		let description = document.createElement("td");
		let resolver = document.createElement("td");
		let status  = document.createElement("td");
		let type = document.createElement("td");

		id.innerHTML = element.re_id;
		amount.innerHTML = element.re_amount;
		submitted.innerHTML = formatDate(element.re_submitted);
		
		if(element.re_resolved==null){
			resolved.innerHTML='------/------';
		} else{
          resolved.innerHTML = formatDate(element.re_resolved);
		}

		description.innerHTML = element.re_description;
		resolver.innerHTML = element.re_resolver;
		switch(element.re_resolver){
			case 0:
				resolver.innerHTML = '------/------';
				break;

			case 1:
				resolver.innerHTML = 'Chandra';
				break;
			case 2:
				resolver.innerHTML = 'Sanisha';
				break;
		}

		// status.innerHTML = element.re_status_id;
		

		switch(element.re_status_id){
        case 1:
			status.innerHTML = 'Pending';
			status.classList.add("text-primary");
			break;
		
		case 2:
			status.innerHTML = 'Approved';
			status.classList.add("text-success");
			break;
		case 3:
			status.innerHTML = 'Denied';
			status.classList.add("text-danger");
			break;
		
		}
		// type.innerHTML = element.re_typeid;

		switch (element.re_typeid){
			case 1:
				type.innerHTML = 'Lodging';
				break;
			case 2:
				type.innerHTML = 'Travel';
				break;
			case 3:
				type.innerHTML = 'Food';
				break;
			case 4:
				type.innerHTML = 'Other';
				break;

		}
		row.appendChild(id);
		row.appendChild(amount);
		row.appendChild(submitted);
		row.appendChild(resolved);
		row.appendChild(description);
		row.appendChild(resolver);
		row.appendChild(status);
		row.appendChild(type);
		table.appendChild(row);
	})

}



function logOut(){
	console.log("inside logout function");
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "http://localhost:8080/ProjectOne/employee/logout.change");
	xhttp.send();
	 xhttp.onreadystatechange = function(){
	 	if(xhttp.readyState == 4 && xhttp.status == 200){
	 		window.location.replace("http://localhost:8080/ProjectOne/home/index.html")
	 	}
	 }
}

function formatDate(dateInMilli) {
    let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    let newDate = new Date(dateInMilli);
    let year = newDate.getFullYear();
    let month = months[newDate.getMonth()];
    let dayNumber = newDate.getDate();
	let fullDate = month + " " + dayNumber + " , " + year;
    return fullDate;
}