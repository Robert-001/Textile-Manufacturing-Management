import React from 'react';
import { Jumbotron } from 'reactstrap';

const LandingPage = () => {
  return (
    <div style={{ margin: 50 }}>
      <Jumbotron>
        <h1 className="display-3">Welcome! :)</h1>
        <hr className="my-2" />
        <p className="lead">This is a Textile Manufacturing Management App.</p> 
      </Jumbotron>
    </div>
  );
};

export default LandingPage;
