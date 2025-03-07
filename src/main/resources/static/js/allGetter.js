document.addEventListener("DOMContentLoaded",function(){
    fetch("http://localhost:9898/Admin/all_getter_request").then(response => response.json())
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
            `;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error("Error fetching data:", error));
})
