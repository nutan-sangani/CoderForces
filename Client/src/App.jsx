import { useState } from 'react'
import Header from './Components/Header'
import Codespace from './Components/Codespace'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import './index.css';
import AllProblems from './Pages/AllProblems';
import Problem from './Pages/Problem';


function App() {

  return (
    <div>
    <Router>
      <Routes>
        <Route path='/'
          element={<AllProblems/>}
        />
        <Route
          path='/problem/:problemId'
          element={<Problem/>}
        />
        <Route
          path='/submission/:submissionId'
          element={<SubmissionPage/>}
        />
      </Routes>
    </Router>
    </div>
  )
}

export default App
