async function getResponse() {
    let response = await fetch('https://jsonplaceholder.typicode.com/users')
    let content = await response.json()

    let list = document.querySelector('.UserView')

    let key;

    for (key in content) {
        list = content[key]
    }

    return (list)

}

let about_user = getResponse()

let user_info = about_user;
console.log(about_user)
console.log(user_info)
export default user_info;