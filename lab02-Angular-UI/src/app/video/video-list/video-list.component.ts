import { Component, Input, Output, EventEmitter } from '@angular/core';

export class Video {
  title: string;
  url: string;
  status: string;
  approved: boolean;
  likes: number;
  unlike: number;
  currentStatus: string;
  exitplayprogress: number;
}

@Component({
  selector: 'app-video-list',
  templateUrl: './video-list.component.html',
  styleUrls: ['./video-list.component.css']
})
export class VideoListComponent {

  @Input() videos: Video;

  @Output() addVideo = new EventEmitter<boolean>();


  constructor() {
  }

  loadVideoAddComponent() {
    this.addVideo.emit(true);
 }




}
