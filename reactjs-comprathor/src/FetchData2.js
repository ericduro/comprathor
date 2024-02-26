import React, { useEffect } from "react";

import { backend } from "./Variables";

const url = backend + '/product/all'

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