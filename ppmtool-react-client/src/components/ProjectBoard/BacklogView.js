import React, { Component } from 'react';
import CreateProjectButton from '../Project/CreateProjectButton';
import {getBacklog} from "../../actions/backlogActions";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import ProjectTaskBacklog from '../ProjectTasks/ProjectTaskBacklog';

class BacklogView extends Component {

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



    render() {
        const {id} = this.props.match.params;
        const {project_tasks} = this.props.backlog;
        const tasks = project_tasks.map(project_task => (
            <ProjectTaskBacklog key = {project_task.id} project_task = {project_task} /> 
            ))
        

        return (
            <div className="projects">
            <div className="container">
              <div className="row">
                <div className="col-md-12">
                  <h1 className="display-4 text-center">Project Tasks</h1>
                  <br />
    
                  <br />
                  <hr />
                    {
                        tasks
                    }
                
                </div>
              </div>
            </div>
          </div>
        )
    }
}


BacklogView.propTypes = {
    getBacklog : PropTypes.func.isRequired,
    backlog : PropTypes.object.isRequired,
    errors : PropTypes.object.isRequired
};

const mapStateToProps = state =>({
    backlog : state.backlog,
    errors : state.errors
});
export default  connect(mapStateToProps, {getBacklog})(BacklogView);