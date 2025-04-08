document.addEventListener("DOMContentLoaded", () => {
    fetch("https://13.114.31.69:9898/allD") // Make sure your endpoint is accessible
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch donors.");
            }
            return response.json();
        })
        .then(data => {
            const tableBody = document.getElementById("data-table");

            data.forEach((donor, index) => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${donor.name || "-"}</td>
                    <td>${donor.username || "-"}</td>
                    <td>${donor.mobile || "-"}</td>
                    <td>${donor.city || "-"}</td>
                    <td>${donor.panCard || "-"}</td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error loading donor data:", error);
            alert("Failed to load donors.");
        });
});
