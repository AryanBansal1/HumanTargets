document.addEventListener("DOMContentLoaded", () => {
    fetch(`${BASE_URL}/allP`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch products");
            }
            return response.json();
        })
        .then(data => {
            const tableBody = document.getElementById("data-table");
            data.forEach((product, index) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${product.id || "-"}</td>
                    <td>${product.item_name || "-"}</td>
                    <td>${product.category || "-"}</td>
                    <td>${product.yearold || "-"}</td>
                    <td>${product.district || "-"}</td>
                    <td>${product.status || "-"}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error loading products:", error);
            alert("Failed to load products. Please try again later.");
        });
});
