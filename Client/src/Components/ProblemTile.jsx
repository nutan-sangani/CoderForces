import React from 'react'
import './css/ProblemTile.css';
import { Link } from 'react-router-dom';

function ProblemTile(props) {
  return (
    <Link to={"/problem/"+props.problemId}>
        <div className='problemTile'>
            <h2>{props.problemId}</h2>
            <h2>{props.problemName}</h2>
        </div>
    </Link>
  )
}

export default ProblemTile