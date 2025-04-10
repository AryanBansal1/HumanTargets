document.addEventListener("DOMContentLoaded", () => {
    const stripe = Stripe("pk_test_51RA4EmAc3Pl7Gj76WwHn7flVHlrgnqIBDTTjWq30ngonYjHB2c3NaBu9qYgd30GGDt4QeZSZWuMVKPZtIVkDBXSM00Iug9sACU");

    const donateBtn = document.getElementById("donate-button");

    donateBtn.addEventListener("click", async function (e) {
        e.preventDefault();

        const name = document.getElementById("amout-donation-name").value.trim();
        const email = document.getElementById("amout-donation-email").value.trim();

        if (!name || !email) {
            alert("Please enter both name and email");
            return;
        }

        let amount = 1000;
        if (document.getElementById("btnradio2").checked) amount = 2000;
        else if (document.getElementById("btnradio3").checked) amount = 3000;

        try {
            const res = await fetch(`${BASE_URL}/api/payment/create-checkout-session`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ name, email, amount }),
            });

            console.log("Response status:", res.status);

            if (!res.ok) {
                const errorText = await res.text();
                console.error("Server returned error page:", errorText);
                throw new Error("Failed to create checkout session");
            }

            const data = await res.json();
            console.log("Stripe checkout URL:", data.checkoutUrl);

            if (data.checkoutUrl) {
                window.location.href = data.checkoutUrl;
            } else {
                throw new Error("checkoutUrl missing in response");
            }

        } catch (error) {
            console.error("Checkout error:", error);
            alert("Something went wrong while processing your donation. Please try again.");
        }
    });
});
