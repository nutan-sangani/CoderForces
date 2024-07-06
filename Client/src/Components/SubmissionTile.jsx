import React from "react";
import {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableBody,
  TableCell,
} from "@mui/material";
import { Link } from "react-router-dom";
import "./css/SubmissionTile.css";

function SubmissionTile(props) {
  return (
    <div className="submissionTable">
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Submission ID</TableCell>
              <TableCell>Problem Name</TableCell>
              <TableCell>Submission Time</TableCell>
              <TableCell>Language</TableCell>
              <TableCell>Status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {props.submissionDetails && (
              <TableRow>
                <TableCell>
                  <Link to={"/submission/output/"+props.submissionDetails.submissionId}>{props.submissionDetails.submissionId}</Link>
                </TableCell>
                <TableCell>
                  <Link to={"/problem/"+props.submissionDetails.problemId}>{props.submissionDetails.problemName}</Link>
                </TableCell>
                <TableCell>{props.submissionDetails.submissionTime}</TableCell>
                <TableCell>
                  {props.submissionDetails.submissionLanguage}
                </TableCell>
                <TableCell>{props.submissionDetails.acceptedStatus}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default SubmissionTile;
