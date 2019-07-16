import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Source } from 'src/app/core/models';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.scss'],
})
export class PlaylistComponent implements OnInit {
  constructor() {}
  selectedIndex = -1;
  @Input() sources: Source[] = [];
  @Output() sourceSelected = new EventEmitter();

  ngOnInit() {}

  handleSelection(index: number) {
    this.selectedIndex = index;
    this.sourceSelected.emit(this.sources[this.selectedIndex]);
  }
}
