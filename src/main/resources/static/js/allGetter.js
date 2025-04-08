document.addEventListener("DOMContentLoaded",function(){
    fetch(`${BASE_URL}/Admin/all_getter_request`).then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById("data-table");
        tableBody.innerHTML = ""; // Clear table before inserting new rows

        data.forEach(request => {
            const row = document.createElement("tr");
            row.innerHTML = `
                    <td>${request.id}</td>
                    <td>${request.username}</td>
                    <td>${request.fullname}</td>
                    <td>${request.phoneNumber}</td>
                    <td>${request.email}</td>
                    <td>${request.rationcardno}</td>
                    <td>
                        <button onclick="approveRequest(${request.id})">Approve</button>
                        <button onclick="denyRequest(${request.id})">Deny</button>
                    </td>
            `;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error("Error fetching data:", error));

    
})

function approveRequest(id) {
    fetch(`${BASE_URL}/Admin/approve_getter/${id}`, {
        method: "POST",
    })
    .then(response => response.json())
    .then(data => {
        alert("Request Approved: " + data.message);
        window.location.reload(); // Reload the page to update status
    })
    .catch(error => console.error("Error approving request:", error));
}

function denyRequest(id) {
    fetch(`${BASE_URL}/Admin/reject_getter/${id}`, {
        method: "POST",
    })
    .then(response => response.json())
    .then(data => {
        alert("Request Denied: " + data.message);
        window.location.reload(); // Reload the page to update status
    })
    .catch(error => console.error("Error denying request:", error));
}
