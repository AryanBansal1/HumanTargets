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
    fetch(`https://13.114.31.69:9898/alldonations?distrcit=${district}`)
        .then(response => response.json())
        .then(data => {
        const container = document.getElementById("donation-container");
        container.innerHTML = "";
        data.forEach(donation => {
            const card = document.createElement("div");
            card.classList.add("card");
            card.innerHTML = `
                 <h2 class="product-title">${donation.item_name}</h2>
         <table class="product-table">
        <tr><td>Category:</td><td>${donation.category}</td></tr>
        <tr><td>City:</td><td>${donation.city}</td></tr>
        <tr><td>Year Old:</td><td>${donation.yearold}</td></tr>
        <tr><td>Status:</td><td>${donation.status}</td></tr>
    </table>
    <button class="book-btn" onclick="bookingitem(${donation.id})">Book Now</button>

    `;
            container.appendChild(card);

           });
        })
        .catch(error => console.error("error fetching donations ",error))
}
function bookingitem(id){
    fetch("/api/current-user")
        .then(response => response.text())
        .then(username => {
            if(username){
                sessionStorage.setItem("username",username);
                console.log("username stored:",username)

                fetch(`https://13.114.31.69:9898/book_donation_item?id=${id}&gettername=${encodeURIComponent(username)}`,{
                    method: "PUT",
                    headers:{
                        "Content-Type":"application/x-www-form-urlencoded",
                    },
                })
                .then(response => {
                    if(response.ok){
                        console.log(`Donation item ${id} booked by ${username}`);
                        alert(`Booking confirmed for item ${id}!`);
                    }
                    else{
                        console.error("Failed to book donation item.");
                        alert("Failed to book donation item. Please try again.");
                    }
                })
                .catch(error => console.error("Error booking donation:", error));
                alert("An error occurred while booking the item.");
            }
            else{
                console.error("user is not logged in");
                alert("You must be logged in to book a donation item.");
            }
        })
        .catch(error => console.error("Error fetching user:", error));
        alert("An error occurred while checking user session.");
}




