document.addEventListener("DOMContentLoaded",()=>{
    const stripe = Stripe("pk_test_51RA4EmAc3Pl7Gj76WwHn7flVHlrgnqIBDTTjWq30ngonYjHB2c3NaBu9qYgd30GGDt4QeZSZWuMVKPZtIVkDBXSM00Iug9sACU");

    let elements,card;
    const donateBtn = document.getElementById("donate-button");

    donateBtn.addEventListener("click", async function (e) {
        e.preventDefault();

        const name = document.getElementById("amout-donation-name").value.trim();
        const email = document.getElementById("amout-donation-email").value.trim();

        if(!name || !email){
            alert("Please enter both name and email");
            return;
        }

        let amount = 1000;
        if(document.getElementById("btnradio2").checked) amount =2000;
        else if(document.getElementById("btnradio3").checked) amount =3000;

        // Create PaymentIntent
        try {
            const res = await fetch(`${BASE_URL}/api/payment/create-checkout-session`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ name, email, amount })
            });
    
            const { checkoutUrl } = await res.json();
            window.location.href = checkoutUrl; // ⏩ Redirect to Stripe
        } catch (error) {
            console.error("Checkout error:", error);
            alert("Something went wrong. Try again.");
        }
        

    });
});

