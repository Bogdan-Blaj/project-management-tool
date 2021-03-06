import React, { Component } from 'react';
import {Link} from "react-router-dom";
import Backlog from './Backlog';
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {getBacklog} from "../../actions/backlogActions";

class ProjectBoard extends Component {

    //constructor to handle errors
    constructor(){
        super();
        this.state = {
            errors : {}
        };
    }

    componentDidMount(){
        const {id} = this.props.match.params;
        this.props.getBacklog(id);
    }

    componentWillReceiveProps(nextProps){
        if(nextProps.errors){
            this.setState({errors : nextProps.errors});
        }
    }

    
    render(){
        const {id} = this.props.match.params;
        const {project_tasks} = this.props.backlog;
        const {errors} = this.state;
        let BoardContent;

    const boardAlgo = (errors, project_tasks) => {
        if(project_tasks.length < 1){
            if(errors.projectNotFound){
                return (
                    <div className="alert alert-danger text-center" role = "alert">
                    {errors.projectNotFound}
                    </div>
                );
            }
            else
            if(errors.projectIdentifier){
                return (
                    <div className="alert alert-danger text-center" role = "alert">
                    {errors.projectIdentifier}
                    </div>
                );
            }

            else {
                return (
                    <div className="alert alert-info text-center">
                    No Project Task on this Board
                    </div>
                );
            }
        }
        else {
            return   <Backlog project_tasks_prop={project_tasks}/>;
        }
    };
    BoardContent = boardAlgo(errors, project_tasks);

        return (
            <div className="container">
                <div className="row">
                    <div className="col-auto mr-auto">
                        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
                        <i className="fas fa-plus-circle"> Create Project Task</i>
                        </Link>
                    </div>

                    <div className="col-auto"> 
                        <Link to={`/backlogView/${id}`} className="btn btn-primary mb-3">
                        <i className="fas fa-plus-circle"> Backlog View</i>
                        </Link> 
                    </div>
                </div>

                   
            <br />
            <hr />
            {BoardContent}
          
        </div>
    
            

        )
    }
}

ProjectBoard.propTypes = {
    getBacklog : PropTypes.func.isRequired,
    backlog : PropTypes.object.isRequired,
    errors : PropTypes.object.isRequired
};

const mapStateToProps = state =>({
    backlog : state.backlog,
    errors : state.errors
});

export default connect(mapStateToProps, {getBacklog})(ProjectBoard);