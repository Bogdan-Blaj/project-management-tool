import React, { Component } from 'react'

class AddProject extends Component {
    constructor(){
        super();

        this.state = {
            projectName: "",
            projectIdentifier: "",
            description: "",
            start_date: "",
            end_date: ""
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    //update state
    onChange(e) {
        this.setState( { [e.target.name] : e.target.value}); //bind state field to form name
    }

    onSubmit(e){
        e.preventDefault();

        const newProject = {
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            start_date: this.state.start_date,
            end_date: this.state.end_date
        };

        console.log(newProject);
    }
    render() {
        return (
            <div>
            {
                //check name atribute input fields
                //create constructor
                //set state
                //set value on input fields
                //create onChange function
                //set onChange on each input field
                //bind on constructor
                //check state change in the react extension

                //_________

                //map field to state
                /*
                    1. create constructor with state
                    2. create on change
                    3. se state based on the target name [e.target.name] : e.target.value to bind the state to form based on his name
                    4. bind each input for the form with the constructor with the onchange = {this.onChange}
                    5. set bind in the constructor this.onChange = this.onChange.bind(this)
                */
               //onSubmit get value
               /*
                    1. add on form a binding on onSubmit = { this.onSubim }
                    2. create onSubmit where you create the Project object based on the values e.g projectName: this.state.projectName
                    3. prevent reset onSubmit  e.preventDefault();
                    4. bind onSubmit to the form
               */
            } 

            <div className="project">
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <h5 className="display-4 text-center">Create Project form</h5>
                        <hr />
                        <form onSubmit = {this.onSubmit}>
                            <div className="form-group">
                                <input type="text" className="form-control form-control-lg " placeholder="Project Name" name="projectName" value={this.state.projectName} onChange = {this.onChange} />
                            </div>
                            <div className="form-group">
                                <input type="text" className="form-control form-control-lg" placeholder="Unique Project ID" name="projectIdentifier" value={this.state.projectIdentifier} onChange = {this.onChange} />
                            </div>
              
                            
                            <div className="form-group">
                                <textarea className="form-control form-control-lg" placeholder="Project Description" name="description" value={this.state.description} onChange = {this.onChange} ></textarea>
                            </div>
                            <h6>Start Date</h6>
                            <div className="form-group">
                                <input type="date" className="form-control form-control-lg" name="start_date" value={this.state.start_date} onChange = {this.onChange} />
                            </div>
                            <h6>Estimated End Date</h6>
                            <div className="form-group">
                                <input type="date" className="form-control form-control-lg" name="end_date" value={this.state.end_date} onChange = {this.onChange} />
                            </div>
    
                            <input type="submit" className="btn btn-primary btn-block mt-4" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
        );
    }
}

export default AddProject;
