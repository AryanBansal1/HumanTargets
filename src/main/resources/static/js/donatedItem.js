document.addEventListener("DOMContentLoaded",function(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(gotlocation,failedToGetlocation)
    }
    else{
        document.getElementById("para-id").innerHTML = "Geolocation is not supported by this browser."
    }
})

function gotlocation(position){
    let latitude = position.coords.latitude
    let longitude = position.coords.longitude

    let url = `https://api.opencagedata.com/geocode/v1/json?q=${latitude}+${longitude}&key=d5de115436e04b7796331dbf9b8f3843`
    fetch(url)
        .then(response => response.json())
        .then(data =>{
            let district = data.results[0].components.state_district
            console.log("The city detected is ",district)
            sendCitytobackend(district)
        })
        .catch(error =>console.error("error fetching the location",error))

    
}
function failedToGetlocation(){
    console.log("Please give access to show you resources in your region")
}

function sendCitytobackend(district){
    fetch(`${BASE_URL}/alldonations?distrcit=${district}`)
        .then(response => response.json())
        .then(data => {
        const container = document.getElementById("donation-container");
        container.innerHTML = "";
        data.forEach(donation => {
            const card = document.createElement("div");
            card.classList.add("card", "card-box");
            card.innerHTML = `
                 <h2 class="product-title">${donation.item_name}</h2>
         <table class="product-table">
        <tr>
            <td><strong>Category:</strong></td>
            <td>${donation.category}</td>
        </tr>
        <tr>
            <td><strong>City:</strong></td>
            <td>${donation.city}</td>
        </tr>
        <tr>
            <td><strong>Year Old:</strong></td>
            <td>${donation.yearold}</td>
        </tr>
        <tr>
            <td><strong>Status:</strong></td>
            <td>${donation.status}</td>
        </tr>
    </table>
    <button class="book-btn" onclick="bookingitem(${donation.id})">Book Now</button>
`;
            container.appendChild(card);

           });
        })
        .catch(error => console.error("error fetching donations ",error))
}
function bookingitem(id) {
    fetch("/api/current-user")
        .then(response => response.text())
        .then(username => {
            if (username) {
                sessionStorage.setItem("username", username);
                console.log("username stored:", username);

                fetch(`${BASE_URL}/book_donation_item?id=${id}&gettername=${encodeURIComponent(username)}`, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                    },
                })
                .then(response => response.json().then(body => ({
                    status: response.status,
                    body
                })))
                .then(({ status, body }) => {
                    if (status === 200) {
                        console.log(`Donation item ${id} booked by ${username}`);
                        alert(body.message); // Show success message from backend
                    } else {
                        console.error("Failed to book donation item:", body.message);
                        alert("Error: " + body.message); // Show error message from backend
                    }
                })
                .catch(error => {
                    console.error("Error booking donation:", error);
                    alert("An unexpected error occurred while booking the item.");
                });
            } else {
                console.error("User is not logged in");
                alert("You must be logged in to book a donation item.");
            }
        })
        .catch(error => {
            console.error("Error fetching user:", error);
            alert("An error occurred while checking user session.");
        });
}

    




