import React from "react";
import { useState } from "react";
import { Button } from "@mui/material";
import "./css/Codespace.css";
import axios from "axios";

function Codespace() {
  const [code, setCode] = useState("");

  function submitCodeChange(event) {
    setCode(event.target.value);
  }

  function codeSubmitHandler(){
    let body = {}
    body.code = code;
    body.language="java";
    body.problemId="1";
    body.userId="1"
    // console.log(body);
    axios.post("http://localhost:8080/judge/submit",body)
        .then((res)=>{
            console.log(res.data);
            alert(res.data);
        })
        .catch((error) => {
            console.log(error);
            alert("Error check log");
        });
  }

  return (
    <div>
      {/* <form onSubmit={() => codeSubmitHandler()}> */}
        <textarea
          value={code}
          onChange={(event) => submitCodeChange(event)}
          className="code__input"
        />
        <div className="submit__button">
          <Button onClick={()=>codeSubmitHandler()} color="success" size="small" variant="contained">
            Submit
          </Button>
        </div>
      {/* </form> */}
    </div>
  );
}

export default Codespace;
