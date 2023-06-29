window.addEventListener('DOMContentLoaded', function(){

    const volver= document.querySelector(".btnVolver")
    volver.addEventListener('click',function(){
        window.location.href = "index.html";
    })

    const turnoHtml = document.querySelector(".listarTurno")
    const btn = document.querySelector(".btn-listar")
    const btnBuscar = document.querySelector("#formularioBuscar button")
    let listaGenerada = false;
    btn.addEventListener('click',function(event){
        
        event.preventDefault()

        if (!listaGenerada) {
            const rawResponse = fetch('turnos', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            });

        console.log(rawResponse)

        rawResponse.then(function(response) {
            return response.json();
        }).then(function(data) {
            turnoHtml.innerHTML="";
            // Aquí puedes trabajar con los datos en formato JSON
            for(const turno of data){
                turnoHtml.innerHTML += `<article id="art-list">
                <h2>Odontologo</h2>
                <h3>ID: `+ turno.id +`</h3>
                <h3>Paciente: `+ turno.paciente +`</h3>
                <h3>Odontologo: `+ turno.odontologo +`</h3>
                <h3>Fecha y Hora: `+ turno.fechaYHora +`</h3>
    
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

        
        const rawResponse = fetch('turnos/'+idBuscar, {
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
            
            turnoHtml.innerHTML = `<article id="artList">
            <h2>Odontologo</h2>
            <h3>ID: `+ data.id +`</h3>
            <h3>Paciente: `+ data.paciente +`</h3>
            <h3>Odontologo: `+ data.odontologo +`</h3>
            <h3>Fecha y Hora: `+ data.fechaYHora +`</h3>

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