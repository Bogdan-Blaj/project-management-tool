import {GET_PROJECTS, GET_PROJECT, DELETE_PROJECT} from "../actions/types";

const initialState = {
    projects: [],
    project: {}
};

export default function(state = initialState, action){
    switch(action.type){
       case GET_PROJECTS:

        return {
            /*
            <Component x={} y={} z={} />
            Thus instead you do this, wrap them up in an object and use the spread notation

            var props = { x: 1, y: 1, z:1 };
            <Component {...props} />
            */
           ...state,
           projects : action.payload// what we are sending to the store/returning to the server
        };

        case GET_PROJECT:
            return {
                ...state,
                project : action.payload
            };

            case DELETE_PROJECT:
                return{
                    ...state,
                    projects: state.projects.filter(project=>project.projectIdentifier != action.payload)
                };

        default:
            return state;
    }
}