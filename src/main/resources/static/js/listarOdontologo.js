window.addEventListener('DOMContentLoaded', function(){

    const volver= document.querySelector(".btnVolver")
    volver.addEventListener('click',function(){
        window.location.href = "index.html";
    })

    const odontologoHtml = document.querySelector(".listarOdontologo")
    const btn = document.querySelector(".btn-listar")
    const btnBuscar = document.querySelector("#formularioBuscar button")
    let listaGenerada = false;
    btn.addEventListener('click',function(event){
        
        event.preventDefault()

        if (!listaGenerada) {
            const rawResponse = fetch('odontologos', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            });

        console.log(rawResponse)

        rawResponse.then(function(response) {
            return response.json();
        }).then(function(data) {
            odontologoHtml.innerHTML="";
            // Aquí puedes trabajar con los datos en formato JSON
            for(const odontologo of data){
                odontologoHtml.innerHTML += `<article id="artList">
                <h2>Odontologo</h2>
                <h3>ID: `+ odontologo.id +`</h3>
                <h3>Matricula: `+ odontologo.matricula +`</h3>
                <h3>Nombre: `+ odontologo.nombre +`</h3>
                <h3>Apellido: `+ odontologo.apellido +`</h3>
    
                </article>`
            }
            
            console.log(data);
        }).catch(function(error) {
            // Manejo de errores
            console.error(error);
        });
        //const listadoPaciente = rawResponse.json();
        
        listaGenerada = true;
        }
    })


    btnBuscar.addEventListener('click',function(event){
        
        event.preventDefault()
        const idBuscar= document.querySelector("#idBuscar").value;

        
        const rawResponse = fetch('odontologos/'+idBuscar, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        });

        console.log(rawResponse)

        rawResponse.then(function(response) {
            return response.json();
        }).then(function(data) {
            // Aquí puedes trabajar con los datos en formato JSON
            
            odontologoHtml.innerHTML = `<article id="artList">
            <h2>Odontologo</h2>
            <h3>ID: `+ data.id +`</h3>
            <h3>Matricula: `+ data.matricula +`</h3>
            <h3>Nombre: `+ data.nombre +`</h3>
            <h3>Apellido: `+ data.apellido +`</h3>

            </article>`
            
            console.log(data);
        }).catch(function(error) {
            // Manejo de errores
            console.error(error);
        });
        //const listadoPaciente = rawResponse.json();
        listaGenerada = false;
        
    })

})