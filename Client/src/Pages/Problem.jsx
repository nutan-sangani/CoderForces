import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import "./css/Problem.css";
import Codespace from "../Components/Codespace.jsx";

function Problem() {
    const {problemId} = useParams();
    const [problem,setProblem] = useState("");

    useEffect(()=>{
        axios.get("http://localhost:8080/problem/data/1")
        .then((res)=> {
          setProblem(res.data);
          // console.log(res.data);
          // alert(res.data);
        })
        .catch((err)=> console.log(err));
    },[]);
  return (
    <div>
      <div className='question'>
      <h1>
        Given an array of integers nums and an integer target, return whether the target can be formed by taking sum of any two elements from the error. You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order. Input Format : t n target a1 a2 a3 a4 a5 a6.... ak where t is the test case and n is array size, target is the target and in the next lines there are n elements. Output Format : Yes or No (please remember the output is case sensitive)
      </h1>
      </div>
      <Codespace/>
    </div>
  )
}

export default Problem