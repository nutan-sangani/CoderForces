import axios from 'axios';
import React, { useEffect, useState } from 'react'
import ProblemTile from '../Components/ProblemTile';

export default function AllProblems() {

    const [problems,setProblems] = useState([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/problem/all")
        .then((res)=>{
            console.log(res.data);
            setProblems(res.data);
        })
    },[]);
    

  return (
    <div>
        {problems && problems.map((problem)=>{
            return <ProblemTile
            problemId={problem.problemId}
            problemName={problem.name}
            key={problem.problemId}
            />
        })}
    </div>
  )
}
