async function getId() {
    let response = await fetch('/api/', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=8'
        },
        body: JSON.stringify(user)
    });

    let results = await response.json();

    results.forEach(result => {
        console.log(`name: ${result.id}`)
    })
}

getId()

export default function getId();




async function PostRequest() {

    const responseData = await fetch('/api/', {
        method: 'POST',
        headers: {
            'Content-type': 'applications/json;charset=utf-8'
        },
        body: JSON.stringify(userData)
    })
    
    const responseJSON = await responseData.json()

}

PostRequest()

export default function PostRequest();