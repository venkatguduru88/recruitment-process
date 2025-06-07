import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApplyJobService {

   private apiUrl = 'http://localhost:8080/api/upload'; // replace with your real endpoint

  constructor(private http: HttpClient) {}

  submitApplication(data: FormData): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }
}
