import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Source } from '../models';

@Injectable()
export class SourceService {
  constructor(private http: HttpClient) {}
  getSources() {
    return this.http.get<Source[]>('/sources?_sort=id&_order=desc');
  }

  saveSource(source: Source) {
    source.approved = false;
    return this.http.post('/sources', source);
  }

  updateSource(id: number, source: Source) {
    source.approved = false;
    return this.http.put<any>(`/sources/${id}`, source);
  }

  approveSource(id: number) {
    return this.http.patch(`/sources/${id}`, { approved: true });
  }

  deleteSource(id: number) {
    return this.http.delete(`/sources/${id}`);
  }

  getApprovedSources() {
    return this.http.get<Source[]>('/sources?approved=true');
  }
}
