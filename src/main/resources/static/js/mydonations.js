document.addEventListener("DOMContentLoaded", function () {
    // Fetch the logged-in user's username
    fetch("/api/current-user")
        .then(response => response.text())
        .then(username => {
            if (username) {
                sessionStorage.setItem("username", username);
                console.log("Username stored:", username);

                // Fetch donations only after the username is set
                fetchDonations(username);
            } else {
                alert("User not logged in");
            }
        })
        .catch(error => console.error("Error fetching user:", error));
});

// Function to fetch donations based on username
function fetchDonations(username) {
    fetch(`http://localhost:9898/myallDonations?username=${username}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("donation-table");
            tableBody.innerHTML = "";

            data.forEach(donation => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${donation.id}</td>
                    <td>${donation.item_name}</td>
                    <td>${donation.category}</td>
                    <td>${donation.city}</td>
                    <td>${donation.yearold}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching donations:", error));
}
