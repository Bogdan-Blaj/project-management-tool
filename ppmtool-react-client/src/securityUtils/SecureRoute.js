import React, { Component } from 'react';
import {Route, Redirect} from "react-router-dom";
import { connect } from "react-redux"; //check if user is logged 
import PropTypes from "prop-types";

const SecuredRoute = ({ component: Component, security, ...otherProps }) => (
    <Route
      {...otherProps} //get all props
      render={props => //render props and send to components if we are logged in
        security.validToken === true ? (
          <Component {...props} />
        ) : (
          <Redirect to="/login" /> 
        )
      }
    />
  );

  SecuredRoute.propTypes = {
    security: PropTypes.object.isRequired
  };
 
   const mapStateToProps = state => ({
    security: state.security
  });
 
   export default connect(mapStateToProps)(SecuredRoute);