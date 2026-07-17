const menuIcon = document.querySelector('#menu-icon');
const navLinks = document.querySelector('.nav-links');

menuIcon.onclick = () => {
    navLinks.classList.toggle('active');
}

const contactForm = document.getElementById('contactForm');
const formResult = document.getElementById('formResult');

contactForm.addEventListener('submit', function (e) {
    e.preventDefault();
    formResult.innerText = "Sending...";

    const formData = new FormData(contactForm);
    const object = Object.fromEntries(formData);
    const json = JSON.stringify(object);

    fetch('https://api.web3forms.com/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: json
    })
        .then(async (response) => {
            let jsonResponse = await response.json();
            formResult.classList.remove('success', 'error');
            if (response.status == 200) {
                formResult.innerText = "Message sent successfully!";
                formResult.classList.add('success');
            } else {
                formResult.innerText = jsonResponse.message || "Something went wrong.";
                formResult.classList.add('error');
            }
            contactForm.reset();
        })
});