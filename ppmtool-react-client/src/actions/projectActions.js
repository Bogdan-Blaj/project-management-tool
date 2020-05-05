import axios from "axios";
 import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

 export const updateProject = (project, history) => async dispatch => {
  try {
    const res = await axios.put("/api/project", project);
    history.push("/dashboard");
    dispatch({

      type: GET_ERRORS,
      payload: {}
    })
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

 export const createProject = (project, history) => async dispatch => {
   try {
     const res = await axios.post("/api/project", project);
     history.push("/dashboard");
   } catch (err) {
     dispatch({
       type: GET_ERRORS,
       payload: err.response.data
     });
   }
 };

 export const getProjects = () => async dispatch => {
    const res = await axios.get("/api/project/all");
    dispatch({
      type : GET_PROJECTS,
      payload : res.data
    })
 };

//id is from the link
 export const getProject = (id, history) => async dispatch => {
    try {
      const res = await axios.get(`/api/project/${id}`);
    dispatch({
      type : GET_PROJECT,
      payload : res.data 
    })
    } catch (error) {
      history.push("/dashboard"); //when we request for an invalid project we redirect to the dashboard 
    }
 };

 export const deleteProject = id => async dispatch => {
   if( window.confirm("Are you sure you want to delete the project?"))
   {
      await axios.delete(`/api/project/${id}`);
      dispatch ({
        type: DELETE_PROJECT,
        payload: id
      });
   }

 }