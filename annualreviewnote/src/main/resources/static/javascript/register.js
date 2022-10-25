
const registerForm = document.getElementById('register-form')
const registerUsername = document.querySelector('#register-username')
const registerUserEmail = document.querySelector('#register-userEmail')
const registerPassword = document.querySelector('#register-password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/users'


const handleSubmit = async (e) =>{
console.log("insideeeeee")
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        userEmail: registerUserEmail.value,
        password: registerPassword.value
    }

    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        window.location.replace(responseArr[0])
    }
}
console.log(registerForm)
registerForm.addEventListener("submit", handleSubmit)


