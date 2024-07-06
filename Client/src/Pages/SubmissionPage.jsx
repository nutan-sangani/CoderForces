import axios from 'axios';
import { useScroll } from 'framer-motion';
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import SubmissionTile from '../Components/SubmissionTile';

function SubmissionPage() {

    const {submissionId }= useParams();
    const [submissionDetails,setSubmissionDetails] = useState({});

    useEffect(()=>{
        axios.get("http://localhost:8080/submission/details/"+submissionId)
            .then((res)=>{
                setSubmissionDetails(res.data);
            })
    },[]);

  return (
    <div>
        <SubmissionTile submissionDetails={submissionDetails}/>
    </div>
  )
}

export default SubmissionPage;