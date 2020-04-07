import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Report} from "../model/report";


@Injectable()
export class ReportsService {

    constructor(private http: HttpClient) {

    }

    findAllReports(): Observable<Report[]> {
        return this.http.get<Report[]>("http://localhost:8080/api/reports")
    }


}
