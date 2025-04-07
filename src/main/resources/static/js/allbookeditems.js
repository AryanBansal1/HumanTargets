document.addEventListener("DOMContentLoaded",function(){
    fetch("http://13.114.31.69:9898/Admin/all-booked-items").then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById("item-table");
        tableBody.innerHTML = ""; //clear table before inserting new rows

        data.forEach(request => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${request.id}</td>
                <td>${request.item_name}</td>
                <td>${request.category}</td>
                <td>${request.yearold}</td>
                <td>${request.district}</td>
                <td>${request.status}</td>
                <td>${request.donatedby}</td>
                <td>${request.bookedby}</td>
            `;
            tableBody.appendChild(row);
        });
        
    })
    .catch(error => console.error("error fetching data: ",error));
})