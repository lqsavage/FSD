import { Component, OnInit } from '@angular/core';
import { SourceService } from '../core/services';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import {
  FormGroup,
  FormControl,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Source } from '../core/models';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-source',
  templateUrl: './source.component.html',
  styleUrls: ['./source.component.css'],
})
export class SourceComponent implements OnInit {
  sources: Source[] = [];

  // two ways to build a form group.
  sourceForm = new FormGroup({
    title: new FormControl('', Validators.required),
    url: new FormControl('http://', [
      Validators.required,
      Validators.pattern(/https?:\/\/\w+/),
    ]),
  });

  sourceEditForm = this.fb.group({
    title: ['', Validators.required],
    url: [''],
  });

  constructor(
    private sourceService: SourceService,
    private fb: FormBuilder,
    private modalService: NgbModal,
    config: NgbModalConfig,
    private toastr: ToastrService
  ) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.sourceService.getSources().subscribe(sources => {
      this.sources = sources;
    });
  }

  save() {
    const { title, url } = this.sourceForm.value;

    const source = {
      id: Date.now(),
      title,
      url,
      duration: '',
      approved: false,
    };

    this.sourceService.saveSource(source).subscribe(
      res => {
        this.sources.unshift(source);
        this.sourceForm.reset({ url: 'http://' });

        this.toastr.success('Save success!', 'Success');
      },
      error => {
        console.error(error);
      }
    );
  }

  edit(content, cid) {
    const idx = this.sources.findIndex(c => c.id === cid);
    const source = this.sources[idx];

    this.sourceEditForm.setValue({ title: source.title, url: source.url });

    this.modalService.open(content, { size: 'lg' }).result.then(
      _ => {
        const source2save = {
          ...source,
          ...this.sourceEditForm.value,
        };
        this.sourceService.updateSource(cid, source2save).subscribe(
          res => {
            console.log(res);

            this.sources.splice(idx, 1, source2save);
            this.toastr.success('Update success!', 'Success');
          },
          error => {
            console.error(error);
          }
        );
      },
      f => f
    );
  }

  delete(content, cid) {
    this.modalService.open(content, { centered: true }).result.then(
      _ => {
        this.sourceService.deleteSource(cid).subscribe(
          res => {
            console.log(res);

            const idx = this.sources.findIndex(c => c.id === cid);
            this.sources.splice(idx, 1);

            this.toastr.success('Delete success!', 'Success');
          },
          error => {
            console.error(error);
          }
        );
      },
      f => f
    );
  }

  approve(cid) {
    this.sourceService.approveSource(cid).subscribe(
      res => {
        const idx = this.sources.findIndex(c => c.id === cid);
        const source = this.sources[idx];
        this.sources.splice(idx, 1, { ...source, approved: true });

        this.toastr.success('Approve success! Enjoy this video.', 'Success');
      },
      error => {
        console.error(error);
      }
    );
  }
}
