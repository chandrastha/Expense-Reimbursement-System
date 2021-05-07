window.onload = function () {
	getSessUser();
	getAllReimbRequest();
	window.history.forward();
    function noBack() { window.history.forward(); }
}

function getSessUser() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function () {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let user = JSON.parse(xhttp.responseText);
			// console.log(user);
			// console.log(xhttp.responseText);
			document.getElementById("welcome").innerText = `Welcome ${user.firstname}`;
		}
	}

	xhttp.open("GET", "http://localhost:8080/ProjectOne/getsessionuser.json");

	xhttp.send();
}

function getAllReimbRequest() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function () {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let reimbRequests = JSON.parse(xhttp.responseText);

			// console.log(reimbRequests);
			console.log("inside get all reimb");
			domManipulation(reimbRequests);
		}
	}

	xhttp.open("GET", "http://localhost:8080/ProjectOne/manager/getreimbursements.json", true);
	xhttp.send();
}

function reimbFilterByType() {
	let reimbType = document.getElementById("reimbType").value;
	var reimbTypeInText;

	switch (reimbType) {
		case "1":
			reimbTypeInText = "Lodging";
			break;
		case "2":
			reimbTypeInText = "Travel";
			break;
		case "3":
			reimbTypeInText = "Food";
			break;
		case "4":
			reimbTypeInText = "Other";
			break;
	}
	let table = document.getElementById("tbody");
	tr = table.getElementsByTagName("tr");

	for (var i = 0; i < tr.length; i++) {
		let td = tr[i].getElementsByTagName("td")[8].innerHTML;

		console.log(reimbTypeInText);
		if (td === reimbTypeInText) {
			tr[i].style.display = "";
		}
		else {
			tr[i].style.display = "none";
		}
	}

	if (reimbType == 5) {
		for (var i = 0; i < tr.length; i++) {
			tr[i].style.display = "";
		}
	}
}

function reimbFilterByStatus() {
	let reimbStatus = document.getElementById("reimbStatus").value;
	let reimbStatusinText;

	switch (reimbStatus) {
		case "1":
			reimbStatusinText = "Pending";
			break;
		case "2":
			reimbStatusinText = "Approved";
			break;
		case "3":
			reimbStatusinText = "Denied";
			break;
	}

	let table = document.getElementById("tbody");
	tr = table.getElementsByTagName("tr");
	for (var i = 0; i < tr.length; i++) {
		let td = tr[i].getElementsByTagName("td")[7].innerHTML;

		// console.log(reimbStatusinText);
		if (td === reimbStatusinText) {
			tr[i].style.display = "";
		}
		else {
			tr[i].style.display = "none";
		}
	}
	if (reimbStatus == 4) {
		for (var i = 0; i < tr.length; i++) {
			tr[i].style.display = "";
		}
	}
}



//#tbody > tr:nth-child(1) > td:nth-child(9)
function domManipulation(reimbRequests) {
	reimbRequests.forEach(element => {
		console.log("inside manager dome manipulation js")
		// console.log(element);
		let table = document.getElementById("reimbTable").getElementsByTagName("tbody")[0];
		let row = document.createElement("tr");

		let id = document.createElement("td");
		let amount = document.createElement("td");
		let submitted = document.createElement("td");
		let resolved = document.createElement("td");
		let description = document.createElement("td");
		let author = document.createElement("td");
		let resolver = document.createElement("td");
		let status = document.createElement("td");
		let type = document.createElement("td");

		let approveButton = document.createElement("button");
		approveButton.id = "approveBtn";
		approveButton.innerHTML = "Approve";
		approveButton.setAttribute("data", element.re_id);
		approveButton.setAttribute("type", "button");
		approveButton.classList.add("btn");
		approveButton.classList.add("btn-outline-success");

		let denyButton = document.createElement("button");
		denyButton.id = "denyBtn";
		denyButton.innerHTML = "Deny";
		denyButton.setAttribute("data", element.re_id);
		denyButton.setAttribute("type", "button");
		denyButton.classList.add("btn")
		denyButton.classList.add("btn-outline-danger");


		id.innerHTML = element.re_id;
		amount.innerHTML = element.re_amount;
		submitted.innerHTML = formatDate(element.re_submitted);
		if (element.re_resolved == null) {
			resolved.innerHTML = '------/------';
		} else {
			resolved.innerHTML = formatDate(element.re_resolved);
		}

		// resolved.innerHTML = formatDate(element.re_resolved);
		if (element.re_resolved == null) {
			resolved.innerHTML = '------/------';
		} else {
			resolved.innerHTML = formatDate(element.re_resolved);
		}
		description.innerHTML = element.re_description;
		// author.innerHTML = element.re_author;

		switch (element.re_author){
			case 3:
				author.innerHTML = 'Nitu';
				break;

				case 4:
				author.innerHTML = 'Kumar';
				break;

				case 5:
				author.innerHTML = 'Ann';
				break;

				case 6:
				author.innerHTML = 'George';
				break;
		}
		// resolver.innerHTML = element.re_resolver;
		switch (element.re_resolver) {
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
		switch (element.re_status_id) {
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

		if(element.re_status_id == 2 || element.re_status_id == 3 ){
			approveButton.setAttribute("style", "display:none");
			denyButton.setAttribute("style", "display:none");
		}
		// type.innerHTML = element.re_typeid;
		switch (element.re_typeid) {
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
		row.appendChild(author);
		row.appendChild(resolver);
		row.appendChild(status);
		row.appendChild(type);
		row.appendChild(approveButton);
		row.appendChild(denyButton);
		table.appendChild(row);

	});

}

document.addEventListener('click', function (event) {
	if (event.target && event.target.id == "approveBtn") {
		let xhttp = new XMLHttpRequest();
		let updatedReimb = {
			re_id: event.target.getAttribute('data'),
			re_amount: 0,
			re_submitted: "",
			re_resolved: "",
			re_description: "",
			re_author: "",
			re_resolver: "",
			re_status_id: 3,
			re_typeid: ""
		}
		let updatedReimbString = JSON.stringify(updatedReimb);
		xhttp.open("POST", "http://localhost:8080/ProjectOne/manager/approve.json");
		xhttp.send(updatedReimbString);

		xhttp.onreadystatechange = function () {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				document.getElementById("reimbTable").getElementsByTagName("tbody")[0].innerHTML = "";
				getAllReimbRequest();
			}
		}
	}
})

document.addEventListener('click', function (event) {
	if (event.target && event.target.id == "denyBtn") {
		let xhttp = new XMLHttpRequest();
		let updatedReimb = {
			re_id: event.target.getAttribute('data'),
			re_amount: 0,
			re_submitted: "",
			re_resolved: "",
			re_description: "",
			re_author: "",
			re_resolver: "",
			re_status_id: 0,
			re_typeid: ""
		}
		let updatedReimbString = JSON.stringify(updatedReimb);
		xhttp.open("POST", "http://localhost:8080/ProjectOne/manager/deny.json");
		xhttp.send(updatedReimbString);

		xhttp.onreadystatechange = function () {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				document.getElementById("reimbTable").getElementsByTagName("tbody")[0].innerHTML = "";
				getAllReimbRequest();
			}
		}
	}
})

function logOut(){
	console.log("inside logout function");
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "http://localhost:8080/ProjectOne/manager/logout.change");
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