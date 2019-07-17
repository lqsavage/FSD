import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Video } from '../model/video';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class VideoServiceService {

  constructor(private http: HttpClient) { }

  /** GET videos from the json server */
  getVideoData (): Observable<Video[]> {
    return this.http.get<Video[]>('http://localhost:3000/youtube')
    .pipe(
      catchError(this.handleError)
    );
  }

  /** POST: add a new vidoe to the json server */
  addVideoData(video: Video): Observable<Video> {
    return this.http.post<Video>('http://localhost:3000/youtube', video, httpOptions)
    .pipe(
      catchError(this.handleError)
    );
  }

  /** DELETE: delete the video from the server */
  delVideoData(video: Video | number):  Observable<Video> {
    const id = typeof video === 'number' ? video : video.id;
    return this.http.delete<Video>(`http://localhost:3000/youtube/${id}`, httpOptions);
  }

  /** PUT: update the video on the server */
  updateVideo (video: Video | number): Observable<any> {
    const id = typeof video === 'number' ? video : video.id;
    return this.http.put(`http://localhost:3000/youtube/${id}`, video, httpOptions);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }

}
