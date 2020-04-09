import {Result} from "./result";

export interface Report {
    id: string;
    processTime: string;
    results: Result[];
}

