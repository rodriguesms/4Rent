import { combineReducers } from 'redux'
import accountReducer from './accounteReducer'

const rootReducer = combineReducers({
    account: accountReducer
});

export default rootReducer;