import React, { useEffect } from "react";

const url = 'localhost:8080/product/all'

const FetchData2 = () => {
    let fetchData = async ()=>{
        let resp = await fetch(url);
        let final = await resp.json();
        console.log(final);
    }

    useEffect(()=>{
        fetchData();
    },[])

    return (
        <h1>Test</h1>
    )
}

export default FetchData2