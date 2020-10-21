import {LoginState} from "../../../model/LoginState";
import {AnyAction} from "redux";

export function login(loginState:LoginState = {}, action:AnyAction) {
    return loginState;
}