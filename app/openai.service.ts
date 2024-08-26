import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OpenaiService {

  private apiUrl = 'http://localhost:8080/api/openai/prompt';

  constructor(private http: HttpClient) { }

  getOpenAIResponse(prompt: string): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<string>(this.apiUrl, prompt, { headers: headers });
  }
}
