import { Pipe, PipeTransform } from '@angular/core';
import {Result} from "../model/result";

@Pipe({ name: 'ReportResultPipe' })
export class ReportResultPipePipe implements PipeTransform {
    transform(results: Result[]) {
        return results.map(res => res.reference + " - " + this.mapCode(res)).join("\n");
    }

    private mapCode(res: Result) {
        switch(res.code) {
            case 1000: {
                return "success";
                break;
            }
            case 3001: {
                return "duplication";
                break;
            }
            case 3002: {
                return "invalid balance";
                break;
            }
            default: {
                return "unknown";
                break;
            }
        }
    }
}
