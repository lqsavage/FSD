import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Video } from '../model/video';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VideoServiceService {

  constructor(private http: HttpClient) { }

  vidoedata = 'assets/video.json';

  // video: Video;
  // videos: [];
  /** GET videos from the json server */
  getVideoData (): Observable<Video[]> {
    return this.http.get<Video[]>('http://localhost:3000/youtube');
  }

  /** POST: add a new vidoe to the json server */
  addVideoData(video: Video): Observable<Video> {
    return this.http.post<Video>('http://localhost:3000/youtube', video);
  }

  /** DELETE: delete the hero from the server */
  delVideoData(video: Video | number):  Observable<Video> {
    const id = typeof video === 'number' ? video : video.id;
    return this.http.delete<Video>(`http://localhost:3000/youtube/${id}`);
  }

  /** PUT: update the hero on the server */
  updateVideo (video: Video): Observable<any> {
    return this.http.put(`http://localhost:3000/youtube/${video.id}`, video);
  }

}
